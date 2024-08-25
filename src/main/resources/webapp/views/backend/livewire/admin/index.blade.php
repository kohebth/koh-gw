<div>
    <div class="row mt-4">
        <div class="col">
            <input type="text" class="form-control my-2" placeholder=" Search" wire:model.defer="searchTerm" />

            <div class="form-group">
                <x-buttons.search> {{__('labels.buttons.general.search')}}</x-buttons.search>
                <x-buttons.clear> {{__('labels.buttons.general.clear')}}</x-buttons.clear>
            </div>

            <div class="form-horizontal content-loading content-loading">
                <x-loading/>
                <table class="table table-bordered table-hover table-responsive-sm">
                    <thead>
                    <tr>
                        <th>{{ __('labels.backend.admins.fields.name') }}</th>
                        <th>{{ __('labels.backend.admins.fields.username') }}</th>
                        <th>{{ __('labels.backend.admins.fields.status') }}</th>
                        <th>{{ __('labels.backend.admins.fields.roles') }}</th>
                        <th>{{ __('labels.backend.action') }}</th>
                    </tr>
                    </thead>
                    <tbody>
                    @foreach ($admins as $admin)
                        <tr>
                            <td>
                                {{ $admin->display_name }}
                            </td>
                            <td>{{ $admin->username }}</td>
                            <td>
                                {!! $admin->status_label !!}
                            </td>
                            <td>
                                @if($admin->getRoleNames()->count() > 0)
                                    <ul class="fa-ul">
                                        @foreach ($admin->getRoleNames() as $role)
                                            <li><span class="fa-li"><i class="fas fa-check-square"></i></span> {{ ucwords($role) }}</li>
                                        @endforeach
                                    </ul>
                                @endif
                            </td>
                            <td>
                                <a href="{{route('backend.admins.show', $admin)}}" class="btn btn-info btn-sm mt-1" data-toggle="tooltip" title="{{__('labels.backend.show')}}"><i class="fas fa-eye"></i></a>
                                @can('edit_admins')
                                <a href="{{route('backend.admins.edit', $admin)}}" class="btn btn-primary btn-sm mt-1" data-toggle="tooltip" title="{{__('labels.backend.edit')}}"><i class="fas fa-edit"></i></a>
                                @include("backend.livewire.admin.includes.active-action")
                                @endcan
                                @can('delete_admins')
                                <a href="{{route('backend.admins.destroy', $admin)}}" class="btn btn-danger btn-sm mt-1" data-method="DELETE" data-token="{{csrf_token()}}" data-toggle="tooltip" title="{{__('labels.backend.delete')}}" data-confirm="Are you sure?"><i class="fas fa-trash-alt"></i></a>
                                @endcan
                            </td>
                        </tr>
                    @endforeach
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-7">
            <div class="float-left">
                {!! $admins->total() !!} {{ __('labels.backend.total') }}
            </div>
        </div>
        <div class="col-5">
            <div class="float-right">
                {!! $admins->links() !!}
            </div>
        </div>
    </div>
</div>
