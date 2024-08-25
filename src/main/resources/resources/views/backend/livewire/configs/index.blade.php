<div>
    <div class="row mt-4">
        <div class="col">
            <div class="row">
                <div class="form-group col-12">
                    <input type="text" class="form-control my-2" placeholder="Search" wire:model.defer="searchTerm" />
                </div>
            </div>

            <div class="form-group">
                <x-buttons.search> {{__('labels.buttons.general.search')}}</x-buttons.search>
                <x-buttons.clear> {{__('labels.buttons.general.clear')}}</x-buttons.clear>
            </div>

            <div class="form-horizontal content-loading">
                <x-loading/>
                <table class="table table-bordered table-hover table-responsive-sm">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Value</th>
                        <th>Status</th>
                        <th>Description</th>
                        <th>Created by</th>
                        <th>Created Time</th>
                        <th>Updated by</th>
                        <th>Updated Time</th>
                        <th width="125px">{{ __('labels.backend.action') }}</th>
                    </tr>
                    </thead>
                    <tbody>
                    @foreach ($data as $item)
                        <tr>
                            <td>{{ $item->name }}</td>
                            <td class="word-break-40">{{ $item->value }}</td>
                            <td>{{ $item->status === 1 ? 'Active' : 'Inactive' }}</td>
                            <td>{{ $item->description }}</td>
                            <td>{{ $item->createdBy->username ?? '' }}</td>
                            <td>{{ $item->created_at}}</td>
                            <td>{{ $item->updatedBy->username ?? ''}}</td>
                            <td>{{ $item->updated_at }}</td>
                            <td>
                                @include('backend.includes.action_column')
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
                {!! $data->total() !!} {{ __('labels.backend.total') }}
            </div>
        </div>
        <div class="col-5">
            <div class="float-right">
                {!! $data->links() !!}
            </div>
        </div>
    </div>
</div>
