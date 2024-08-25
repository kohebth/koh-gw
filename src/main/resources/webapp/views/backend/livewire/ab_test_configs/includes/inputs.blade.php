<div class="form-group row">
    {{ html()->label('Test Name <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('test_name') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="abTestConfig.test_name" class="form-control">
        @error('abTestConfig.test_name')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>

<div class="form-group row">
    {{ html()->label('Feature <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('feature') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="abTestConfig.feature" class="form-control">
        @error('abTestConfig.feature')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>

<div class="form-group row">
    {{ html()->label('Service Name <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('service_name') }}
    <div class="col-sm-10">
        <select wire:model.defer="abTestConfig.service_name" name="service_name" class="form-control">
            <option value=""></option>
            @foreach($services as $name)
                <option value="{{$name}}">{{$name}}</option>
            @endforeach
        </select>
        @error('abTestConfig.service_name')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>

<div class="form-group row">
    {{ html()->label('Number of groups <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('num_groups') }}
    <div class="col-sm-10">
        <input type="number" wire:model.defer="abTestConfig.num_groups" class="form-control">
        @error('abTestConfig.num_groups')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>

<div class="form-group row">
    {{ html()->label('Enabled groups <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('enable_groups') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="abTestConfig.enable_groups" class="form-control">
        @error('abTestConfig.enable_groups')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>

<div class="form-group row">
    {{ html()->label('Note')->class('col-sm-2 form-control-label')->for('note') }}
    <div class="col-sm-10">
        <p>
            Reduce test size to 0%: enabled_groups = [] <br>
            Consecutive groups, eg: from group 0 - group 15: enabled_groups = 0-15 <br>
            Specific groups, eg: group 5, 8, 10, 20: enabled_groups = [5,8,10,20]
        </p>
    </div>
</div>